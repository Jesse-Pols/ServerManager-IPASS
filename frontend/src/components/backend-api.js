import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 5000
});


export default {
    status() {
        return AXIOS.get('/status');
    },
    hello() {
        return AXIOS.get(`/hello`);
    },
    getUser(userId) {
        return AXIOS.get(`/user/` + userId);
    },
    createUser(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName);
    },
    getSecured(user, password) {
        return AXIOS.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    }
}


