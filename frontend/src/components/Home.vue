<template>
  <div class="home">
    <img src="./../assets/spring-boot-vuejs-logo.png">
    <h1>{{ hellomsg }}</h1>

    <div class="buttons">
        <button @click="setSort('Studenten')">Studenten</button>
        <button @click="setSort('Docenten')">Docenten</button>
        <button @click="setSort('OP')">Onderwijsondersteunend Personeel</button>
        <button @click="check()">Check</button>
    </div>

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
  mounted:function() { this.check(); },
  data () {
      return {
          diensten: [],
          sortBy: 'None'
      }
  },
  methods: {
      check: function() {
        this.diensten = [];
        api.status()
        .then(response => {
            return response.data.Diensten;
        })
        .then(data => {
            // Request Succesful
            for (let i = 0; i < data.length; i++){
                let obj = data[i];
                this.diensten.push({ 
                    id: obj.dienstId,
                    key: obj.key,
                    name: obj.name,
                    relevance: obj.relevance,
                    status: obj.status,
                    score: 0
                });
            }

        })
        .then(data => {
            this.sort(this.diensten);
        })
        .catch(error => {
            // Request Failed
            console.log(error);
        })
      },

      sort: function(data) {

          let switchTo = function (x) {

              for (let i = 0; i < data.length; i++) {

                let obj = data[i];
                obj.score = 0;

                // Objects who are relevant to group x get a score of 2.
                // Objects who have no relevancy are equally relevant to all, and get a score of 1.
                // Objects who are relevant to someone, but not to group x, get a score of 0.

                if (obj.relevance == null)
                    obj.score += 1;

                if (x != 'None' && obj.relevance != null)
                    obj.score += (obj.relevance.includes(x)) * 2;

              }

          }

          switchTo(this.sortBy);

          this.diensten.sort(function(a, b) {
              return (a.score-b.score) * -1;
          });

      },

      setSort: function(id) {
          this.sortBy = id;
          this.check();
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
