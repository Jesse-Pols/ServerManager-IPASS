<template>
  <div class="logs">
    <h1>Logs</h1>
    <DienstList Logs></DienstList>

  </div>

</template>

<script>
import api from './backend-api';
import DienstList from '@/components/modules/DienstList'

export default {
    name: 'logs',
    components: { DienstList }

}
</script>

<style>

</style>
