import axios from 'axios';

// axios 객체 미리 setting
export default axios.create({
    baseURL: 'http://localhost', // Server Port No : 80, Context-root : X
    headers: {
        'Content-type': 'application/json', // JSON으로 보내요~to server
    },
});
