<template>
  <div class="page-header clear-filter" filter-color="black ">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg');"
    ></div>
    <div class="content" style="">
      <div class="container">
        <div style="margin-top:30%">
          <!-- form 투명하게.... -->
          <card plain style="width:500px;height:700px">
            <div style="display:inline">
              <p style="margin-bottom:0px">ID</p>
              <fg-input
                class="no-border input-lg"
                addon-left-icon="now-ui-icons users_circle-08"
                v-model="member.id"
                placeholder="ID"
              >
              </fg-input>
            </div>
            <p style="margin-bottom:0px">PW</p>
            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="PASSWORD"
              style="margin:0px"
              v-model="member.pass"
              @keypress.enter="logingo"
            >
            </fg-input>

            <template slot="raw-content">
              <div class="card-footer text-center">
                <a
                  @click.stop="logingo"
                  class="btn btn-primary btn-round btn-lg btn-block"
                  >Login</a
                >
              </div>
              <div class="pull-left">
                <h6>
                  <a href="#pablo" class="link footer-link">Create Account</a>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">Need Help?</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Card from "src/components/Cards/Card1.vue";
import Button from "src/components/Button.vue";
import FormGroupInput from "src/components/Inputs/formGroupInput.vue";
import { mapGetters } from "vuex";
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput
  },
  data: function() {
    return {
      member: {
        id: "",
        pass: ""
      },
      message: ""
    };
  },
  computed: {
    ...mapGetters(["getAccessToken"]),
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    logingo() {
      this.$store
        .dispatch("login", this.member)
        .catch(({ message }) => (this.msg = message));
    }

    // notifyVue() {
    //   // const color = Math.floor(Math.random() * 4 + 1);
    //   this.$notifications.notify({
    //     message: `아이디와 비밀번호를 확인해주세요`,
    //     icon: "exclamation-triangle",
    //     horizontalAlign: "center",
    //     verticalAlign: "top",
    //     type: "danger"
    //   });
    // }
  },
  filters: {
    short(val) {
      return val.substring(0, 10);
    }
  }
};
</script>
<style></style>
