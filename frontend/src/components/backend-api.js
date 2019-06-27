import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 5000
});


export default {
    status() {
        return AXIOS.get('/status');
    }
}


