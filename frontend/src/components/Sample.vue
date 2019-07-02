<template>
    <div class="sample">
        <h1>Create Dienst!</h1>
        <button @click="createDienst()">Create Dienst</button>
        <div v-if="showResponse"><h6>User created with Id: {{ response }}</h6></div>
        <button v-if="showResponse" @click="retrieveDienst()">Retrieve Dienst {{ dienst.id }} data from database</button>
        <h4 v-if="showRetrievedDienst">Retrieved Dienst {{ retrievedDienst.name }} {{ retrievedDienst.key }}</h4>
    </div>
</template>

<script>
import {AXIOS} from 'axios'

export default {
    name: 'dienst',

    data() {
        return {
            response: [],
            errors: [],
            user: {
                name: '',
                key: ''
            },
            showResponse: false,
            retrievedDienst: {},
            showRetrievedUser: false
        }
    },
    methods: {
        createDienst() {
            var params = new URLSearchParams();
            params.append('name', this.dienst.name);
            params.append('key', this.dienst.key);

            AXIOS.post('/dienst', params)
            .then (response => {
                this.response = response.data;
                this.dienst.id = response.data;
                console.log(response.data);
                this.showResponse = true;
            })
            .catch (err => {
                this.errors.push(err);
            })
        },

        retrieveDienst () {
            AXIOS.get('/dienst/' + this.dienst.id)
            .then(response => {
                this.retrievedDienst = response.data;
                console.log(response.data);
                this.showRetrievedDienst = true;
            })
            .catch (err => {
                this.errors.push(err);
            })
        }
    }

}
</script>

<style>

</style>
