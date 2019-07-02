import axios from 'axios'
import Axios from 'axios';

export const AXIOS = axios.create({
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
        AXIOS.defaults.headers.post['dienst-key'] = dienst.key;
        AXIOS.defaults.headers.post['dienst-name'] = dienst.name;
        AXIOS.defaults.headers.post['dienst-relevance'] = dienst.relevance;
        return AXIOS.post('/dienst/create');
    },
    updateDienst(id, dienst) {
        AXIOS.defaults.headers.post['dienst-name'] = dienst.name;
        AXIOS.defaults.headers.post['dienst-key'] = dienst.key;
        AXIOS.defaults.headers.post['dienst-relevance'] = dienst.relevance;
        return AXIOS.post('/dienst/update/' + id);
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


