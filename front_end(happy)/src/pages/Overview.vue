<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div
          class="col-lg-4 mb-4"
          v-for="(news, index) in getNewsList"
          :key="index"
        >
          <div class="card h-100" style="padding:15px;">
            <div slot="header">
              <h4>{{ news.title }}</h4>
              <center>
                <img class="imgsize" :src="news.img" />
              </center>
            </div>
            <br />
            <div slot="content">
              <p class="card-title">{{ news.content }}</p>
            </div>
            <br />
            <center>
              <div slot="footer">
                <a :href="news.link" class="btn btn-primary">자세히보기</a>
              </div>
            </center>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  components: {},

  created() {
    this.selectNews();
    console.log(this.getNewsList);
  },
  computed: {
    ...mapGetters(["getNewsList"])
  },
  methods: {
    ...mapActions(["selectNews"])
  }
};
</script>
<style></style>
