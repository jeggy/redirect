# 1. Use a standard debian image to find the library
FROM debian:12-slim AS build-env

# 2. Build the final image
FROM gcr.io/distroless/cc-debian12
WORKDIR /app

# Copy the binary
COPY build/bin/linuxX64/releaseExecutable/redirect.kexe /app/redirect

# Copy the missing shared library from the debian image
# Note: Ensure you match the architecture (x86_64-linux-gnu)
COPY --from=build-env /lib/x86_64-linux-gnu/libcrypt.so.1 /lib/x86_64-linux-gnu/libcrypt.so.1

EXPOSE 8080
ENTRYPOINT ["/app/redirect"]