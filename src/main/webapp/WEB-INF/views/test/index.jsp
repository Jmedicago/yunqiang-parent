<html>
  <head>
    <title>云强企业管理系统</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="https://cdn.staticfile.org/jquery/3.4.1/jquery.min.js" type="text/javascript"/>
  </head>
  <body>
    <form action="/article/store.do" method="post">
      <div class="form-group">
        <label for="title">title</label>
        <input type="text" class="form-control" id="title" name="title" placeholder="place input text.."/>
      </div>
      <div class="form-group">
        <label for="subtitle">subtitle</label>
        <input type="text" class="form-control" id="subtitle" name="subtitle" placeholder="place input text.."/>
      </div>
      <div class="form-group">
        <button type="submit">提交</button>
      </div>
    </form>
  </body>
</html>
