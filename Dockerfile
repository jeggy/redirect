# Minimal runtime image for Kotlin/Native Linux x64 binary
FROM gcr.io/distroless/cc-debian12

WORKDIR /app

# Copy the native executable produced by Gradle
# Default K/N output name is redirect.kexe; we rename to just 'redirect'
COPY build/bin/linux/releaseExecutable/redirect.kexe /app/redirect

EXPOSE 8080

ENTRYPOINT ["/app/redirect"]
