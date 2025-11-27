# redirect (Kotlin/Native)

This is a tiny native webserver (no JVM) that provides HTTP redirects based on environment variables.

It listens on port 8080 and supports both `/r/{path}` and `/{path}` forms. The redirect key is the
path lowercased with dashes converted to underscores.

Example:
```sh
docker run --rm -p 8080:8080 \
  -e REDIRECT_my_app=https://google.com \
  jeggy/redirect
```

Now opening `http://localhost:8080/my-app` (or `http://localhost:8080/r/my-app`) redirects to `https://google.com`.

You can provide as many environment variables as you want; they must have prefix `REDIRECT_` and their suffix
becomes the key after lowercasing and replacing `-` with `_`.

Build locally (Linux):
```sh
./gradlew buildNative
# Result: build/bin/linux/releaseExecutable/redirect.kexe
```

Build Docker image locally:
```sh
./gradlew buildNative
docker build -t your-docker-user/redirect:latest .
docker run --rm -p 8080:8080 -e REDIRECT_my_app=https://google.com your-docker-user/redirect:latest
```

Notes:
- This project is Kotlin/Native only; there is no JVM runtime. The produced Docker image is small and fast to start.
- CI publishes the image on release via GitHub Actions using the provided Dockerfile.
