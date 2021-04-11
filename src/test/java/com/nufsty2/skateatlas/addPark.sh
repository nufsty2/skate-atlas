#!/bin/bash
curl localhost:8080/skateparks/add \
-d name="St. George" \
-d lights=true \
-d free=true \
-d inside=false \
-d surface=Concrete \
-d address="171 E 1160 S" \
-d postalCode=84770 \
-d city="St. George" \
-d country=USA \
-d state=UT \
-d website=www.google.com