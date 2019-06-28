<template>
  <div class="home">
    <img src="./../assets/spring-boot-vuejs-logo.png">
    <h1>Welkom!</h1><br><br>
    <DienstList Homepage></DienstList>
  </div>

</template>

<script>
import api from "./backend-api";
import DienstList from "@/components/modules/DienstList"

export default {
  name: 'home',
  components: {
      DienstList
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

#actionButton {
    background-color: #FFCD00;
    border-color: #FFCD00;
}


</style>
