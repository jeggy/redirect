# redirect

Redirect is a very simple webserver that provides redirects coming from environment variables.

Example usage:
```sh
docker run --rm -p 8080:8080 -e REDIRECT_my_app=https://google.com jeggy/redirect
```

Now when opening `localhost:8080/my-app` it will redirect you to `https://google.com`.

You can provide as many environment variables as you want, they just need the prefix `REDIRECT_`
