# YAPoSS - Yet Another Point of Sale System (by Spaghetti ninjas)

## Tooling

__Build tool__: Gradle

__IDE__: IntelliJ IDEA CE (it's free btw)

__Java version__: OpenJDK 17

__Others useful tools__:
- <https://editor.swagger.io> - Online OpenApi viewer

## Building & Running

You must have gradle and docker installed.

All Dockerfiles simply copy the `.jar` files, since building inside takes way too long. To run project:
1. `gradle bootJar` - creates `.jar` for each project.
2. `docker compose build` - builds docker images.
3. `docker compose up` - runs the whole project.

For your convenience: `gradle bootJar && docker compose build && docker compose up`

By default `api-gateway` should be available on `localhost:8080`, see [Taken ports](#taken-ports) for other services.

### Single service

#### Native

Run a service locally with `gradle <PROJECT_NAME>:bootRun`,

For example: `gradle api-gateway:bootRun`

> [!NOTE]
>
> `api-gateway` needs service hostnames and ports set, at the time this documentation is written,
> all services have proper defaults set for native development and with docker.
> 
> These can be overwritten with `SPRING_APPLICATION_JSON` environment variable.
> Example: `SPRING_APPLICATION_JSON='{"customerService":{"hostname":"localhost","port":8081}}' gradle api-gateway:bootRun`.

#### Docker

If you want to run an individual service in docker do this __from project root__:
1. `docker build --tag FOO:BAR -f SERVICE_DIR/Dockerfile .`
2. `docker run -p <PORT_ON_YOUR_SIDE>:<PORT_ON_DOCKER_SIDE> FOO:BAR`

For example:
1. `docker build --tag yaposs:customer-service -f customer-service/Dockerfile .`
2. `docker run -p 8081:8081 yaposs:customer-service`

> [!WARNING]
> 
> Running `api-gateway` this way is unsupported
> (I don't have the time to configure a third way of running it, but you can try to make it happen and open a PR).


## Development guidelines

To save us all some headache please make the default port of each service a different one. This doesn't really matter when running with docker, but causes problems when running multiple services natively.

### Taken ports

Currently, these ports are taken:

| Service          | Port   |
|:-----------------|--------|
| Customer service | `8081` |
| API Gateway      | `8080` |


## Testing

Testing is done with [Hurl](https://hurl.dev). To run them all simply `hurl tests/*.hurl`

## Project generation

This project was generated from a swagger/OpenApi yaml [conf file](contract.yaml).

> [!NOTE]
> You shouldn't need to do these steps, to edit the project edit the generated protobuf files. I'm just documenting the steps.

> [!WARNING]
> Changing `contract.yaml` won't update `pos.proto` or generated java DTO classes.

If you want to replicate the project setup here's the instructions:

1. Generate the project with https://start.spring.io.
1. Install [Go](https://go.dev/dl/)
2. convert `contract.yaml` to swagger spec v2 (this one is a little involved)
1. Serve `contract.yaml` on a http server [this should do](https://crates.io/crates/sfz) (Just run `sfz .`)
2. Use [this converter](https://github.com/LucyBot-Inc/api-spec-converter) by running `api-spec-converter --from=openapi_3 --to=swagger_2 --syntax=yaml http://127.0.0.1:[PORT]/contract.yaml > contractV2.yaml`
3. Set up [openapi2proto](https://github.com/nytimes/openapi2proto)
4. Generate a `.proto` file: `openapi2proto -spec contractV2.yaml -out src/main/proto/dtos.proto -skip-rpcs`
