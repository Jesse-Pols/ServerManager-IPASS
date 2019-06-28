<template>
  <div class="logs">
    <h1>Logs</h1>

    <!-- Lijst met diensten -->
    <div id="dienst_table">
        <div v-for="dienst in diensten" v-bind:key="dienst.id">
            <div class='card'>
                <div class='card-body'>
                    <h6 class='card-title'>
                        {{ dienst.name }}
                    </h6>
                    <p class='card-text'>
                        <div>
                            {{ dienst.status }}
                        </div>
                </div>
            </div>
        </div>
    </div>

  </div>

</template>

<script>
import api from './backend-api';

export default {
    name: 'logs'

}
</script>

<style>

</style>
