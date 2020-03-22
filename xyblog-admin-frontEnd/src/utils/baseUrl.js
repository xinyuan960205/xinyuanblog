let baseUrl= "";   //这里是一个默认的url，可以没有
switch (process.env.NODE_ENV) {
  case 'development':
    baseUrl = "http://localhost:8080"  //开发环境url
    break
  case 'production':
    baseUrl = ""   //生产环境url
    break
}

export default baseUrl;
