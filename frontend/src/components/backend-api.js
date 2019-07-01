import axios from 'axios'
import Axios from 'axios';

const AXIOS = axios.create({
  baseURL: `/api`
});

// Met behulp van AXIOS worden alle request doorgestuurd naar de backend.
// Om een request uit te voeren moet dit bestand geimporteerd worden.
export default {
    getDiensten() {
        return AXIOS.get('/dienst/get');
    },
    getKeyById(id) {
        return AXIOS.get('/dienst/get/' + id);
    },
    createDienst(dienst) {
        return AXIOS.post('/dienst/create/' + dienst.name + '/' + dienst.key);
    },
    createDienstWithRelevance(dienst) {
    	return AXIOS.post('/dienst/create/' + dienst.name + '/' + dienst.key + '/' + dienst.relevance);
    },
    updateDienst(id, dienst) {
        return AXIOS.post('/dienst/update/' + id + '/' + dienst.name + '/' + dienst.key + '/' + dienst.relevance);
    },
    deleteDienst(id) {
        return AXIOS.post('/dienst/delete/' + id);
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


