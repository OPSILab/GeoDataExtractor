# GeoDataExtractor



# Docker support

the project was released with a dockerfile that can be created by executing the following command

```
docker build . -t geodata-be:main
```

After creating the docker, you can execute it using these parameters

```
docker run -d -n geodata-be:main -p 9090:9090
```

# Docker compose support

To facilitate the release, a docker-compose.yml file was created, which can be executed using the following command


```
docker-compose build
```

and after

```
docker-compose up -d
```

# API Exposed

After the execution of the docker locally there is a swagger at:

http://127.0.0.1:9090/swagger-ui/index.html


# Features Available

Available features are:

Extraction of filters and geospatial data from ORION CB.

Management of saved searches on supporting mongodb via CRUD operations (create edit and delete)


# Features Available

Available features are:

Extraction of filters and geospatial data from ORION CB.

Management of saved searches on supporting mongodb via CRUD operations (create edit and delete)

# Future features

The following features will be available in future releases:

Ability to upload and display custom geojson, currently this functionality is available via graphical example on Helsinki municipality.

Input of multiple polygons to perform the search.
