# Rest Api Reference


## GET /drupal/?q=<queryString>

Assuming a config.properties with 
`targetUrl=http://localhost:4777/` and a redirects file 
containing

```
1,project/a
```

redirects like 

```
localhost:4567/drupal/?q=node/1 -> localhost:4777/project/a 
localhost:4567/drupal/?q=de_DE/node/1 -> localhost:4777/project/a
localhost:4567/drupal/?q=/de_DE/node/1 -> localhost:4777/project/a 
localhost:4567/drupal/?q=/en_EN/node/1 -> localhost:4777/project/a 
```

would get executed.

## GET /item/:category/:id_in_category

Assuming a config.properties with 
`targetUrl=http://localhost:4777/`, calls to 

```
localhost:4567/item/typus/9
```

would go  to

```
localhost:4777/èntity/1242001 
```

## GET /books/:alias

Assuming a config.properties with 
`targetUrl=http://localhost:4777/`, calls to 

```
localhost:4567/books/Boisseree1844
```

would go  to

```
localhost:4777/entity/1376714
```