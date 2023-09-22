# GeoDataExtractor

Backend for the implementation of a graphical dashboard for visualizing data stored on the ORION context broker.


# Docker compose 

This Backend comes with a supporting mongodb, so release via compose file only is recommended., which can be executed using the following command


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

# Future features

The following features will be available in future releases:

Ability to upload and display custom geojson, currently this functionality is available via graphical example on Helsinki municipality.

Input of multiple polygons to perform the search.
