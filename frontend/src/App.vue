<template>
  <div id="app">
    <!-- Alles in deze div is zichtbaar op elke pagina -->
    <div id="nav">
      <!-- De navigatiebalk, bovenaan de pagina -->
      <router-link to="/">Home</router-link> |
      <router-link to="/controlpanel">Control Panel</router-link> |
      <router-link to="/sample">Sample</router-link>
    </div>
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
     color: #42b983;
    }
  }
}
</style>
