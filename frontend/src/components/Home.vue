<template>
  <div class="home">
    <img src="./../assets/spring-boot-vuejs-logo.png">
    <h1>{{ hellomsg }}</h1>

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
import api from "./backend-api";

export default {
  name: 'home',
  props: { hellomsg: { type: String, required: true } },
  mounted:function() { 
        //this.check(); 
        api.hello()
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.log(error);
        })
    },
  data () {
      return {
          diensten: []
      }
  },
  methods: {
      check: function() {

          let url = "http://localhost:8098/api/hello";
          this.diensten = [];

          fetch(url, {
              method: "GET"
          })
          .then(response => {
              return response.json();
          })
          .then(data => {
              console.log(JSON.stringify(data, null, 2));
          })
          .catch(err => {
              console.log(JSON.stringify(err));
          })

      }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
</style>
