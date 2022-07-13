import axios from 'axios';

export default axios.create({
  // baseURL: 'http://localhost:5000'
  baseURL: 'http://argenlombiaapp-env.eba-6gyn2yu8.us-east-1.elasticbeanstalk.com'
});