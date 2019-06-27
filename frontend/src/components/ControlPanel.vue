<template>
    <div>
        <h1><b-badge variant="info">Control Panel</b-badge></h1>
        <router-link to="/dienst/Aanmaken"><h5><b-badge variant="primary" id="dienstAanmakenBtn">Nieuwe Dienst</b-badge></h5></router-link>
    </div>
</template>

<script>
import api from './backend-api'
import store from './../store'

export default {
    name: 'controlpanel',

    data () {
        return {
        }
    }

}
</script>

<style>

#dienstAanmakenBtn {
    padding: 10px;
}

</style>
