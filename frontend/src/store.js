import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'
import cookies from './cookies'

Vue.use(Vuex);

// Hier wordt gecheckt of de inloggegevens kloppen.
export default new Vuex.Store({
    state: {
        loginSuccess: false,
        loginError: false,
        userEmail: null,
        userPass: null
    },
    mutations: {
        login_success(state, payload){
            state.loginSuccess = true;
            state.userEmail = payload.userEmail;
            state.userPass = payload.userPass;
        },
        login_error(state, payload){
            state.loginError = true;
            state.userEmail = payload.userEmail;
        }
    },
    actions: {
        login({commit}, {email, password}) {
            return new Promise((resolve, reject) => {
                console.log("Accessing backend with user: '" + email);
                api.getSecured(email, password)
                    .then(response => {
                        console.log("Response: '" + response.data + "' with Statuscode " + response.status);
                        if(response.status == 200) {
                            console.log("Login successful");
                            // place the loginSuccess state into our vuex store
                            commit('login_success', {
                                userEmail: email,
                                userPass: password
                            });
                            //cookie.setCookie("loggedIn", email, 12);
                            cookies.setCookie("loggedIn", email, 12);
                        }
                        resolve(response)
                    })
                    .catch(error => {
                        console.log("Error: " + error);
                        // place the loginError state into our vuex store
                        commit('login_error', {
                            userEmail: email
                        });
                        reject("Invalid credentials!")
                    })
            })
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getUserEmail: state => state.userEmail,
        getUserPass: state => state.userPass
    }
})