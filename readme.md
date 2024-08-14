# Spring AI Example
This is a Spring Boot project that uses [Spring AI](https://spring.io/projects/spring-ai), and demonstrates how to interact with the local Meta-LLama model.

## Setup

* This project intended to work with a local LLM model ([Meta-LLama-3.1-8B](https://huggingface.co/meta-llama/Meta-Llama-3.1-8B)), so you don't need to set up any keys or credentials.
* This project uses Docker to manage the environment. To get started, run the following command to build the Docker
  image and start the container:

```shell
docker-compose up -d
```

### Running Meta-Llama Models

* To run the latest Meta-Llama LLM version (3.1 as of writing) with the 8b model, use the following command
  to pull the image and run it, within the ollama Docker container:

```shell
docker exec -it ollama ollama run llama3.1:8b
```

## APIs
There are 3 use cases that are covered in this project:
1. **Text Generation**: Given a prompt, the model generates text.
2. **Json Generation**: Given a JSON input, the model generates a JSON output based on the given schema.
3. **Vector Embedding**: Given a text input, the model generates a response using the sample embedding vectors.

* You can find sample API requests under the `resources` directory.