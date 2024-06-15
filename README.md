# kosync-server

kosync-server is a self-hostable implementation of the KOReader sync server built with Java. At first this is made as a hobby to publish and mantain a project on a language im familiar with

All information is saved sqlite db.

If you want to use this project theres a published docker container por x86 systems.

```
version: "3.0"
services:
  koreader-sync:
    image: tezzad/kosync-server:latest
    container_name: kosync-server
    volumes:
      - /app/koreader/config:/config
    ports:
      - 8080:8080
    restart: unless-stopped
```

There is a need for a volume, if not, all information is going to be deleted on restart
