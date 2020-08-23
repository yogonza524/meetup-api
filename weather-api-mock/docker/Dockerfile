FROM golang

# Set the Current Working Directory inside the container
WORKDIR /app

# Copy everything from the current directory to the PWD (Present Working Directory) inside the container
COPY ./weather-api-mock /app

# Download all the dependencies
RUN go mod download
#RUN go get -u -v -f all

# Install the package
RUN go install

# Build application
RUN go build main.go

# Remove unnecesary files
RUN rm -rf go.mod go.sum main.go docker scripts docker-compose.yml README.md

# Check directory
RUN ls

# This container exposes port 8080 to the outside world
EXPOSE 8080

# Run the executable
CMD ["./main"]