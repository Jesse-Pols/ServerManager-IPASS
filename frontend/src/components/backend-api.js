import axios from 'axios'
import Axios from 'axios';

const AXIOS = axios.create({
  baseURL: `/api`
});


export default {
    status() {
        return AXIOS.get('/status');
    },
    createDienst(dienst) {
        return AXIOS.post('/dienst/create/' + dienst.name + '/' + dienst.key);
    },
    createDienstWithRelevance(dienst) {
    	return AXIOS.post('/dienst/create/' + dienst.name + '/' + dienst.key + '/' + dienst.relevance);
    },
    getSecured(user, password) {
        return AXIOS.get('/secured/', {
            auth: {
                username: user,
                password: password
            }
        });
    }
}


