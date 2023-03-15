# Auth Server

## Summary
A basic Spring service to manage auth.

## Operation
The service is managed on EC2 using the shared [Release Scripts](https://github.com/basheim/release-scripts) with the
-a identifier.

To build and push a new image, run `sh ./scripts/image_build.sh`.

### Local Run

Environment Variables Required:
* DB_USER
* DB_PASSWORD
* DB_HOST
* DB_PORT
* DB_NAME
* AWS_ACCESS_KEY_ID
* AWS_SECRET_ACCESS_KEY

Command line: run `./gradlew bootRun`

Better to set up an intellij gradle configuration to do the same thing with debugging.