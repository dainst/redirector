# Rest Api Reference

The examples assume 
a config.properties with 
`targetUrl=http://localhost:4777/` and `serverPort=4567`.

## GET /drupal/?q=&lt;queryString&gt;

Assuming a redirects file 
containing

```
257=project/syrher
```

all requests like

```
localhost:4567/drupal/node/257 
localhost:4567/drupal/?q=node/257
localhost:4567/drupal/?q=de_DE/node/257
localhost:4567/drupal/?q=/de_DE/node/257
localhost:4567/drupal/?q=/en_EN/node/257 
```

would get redirected to 

```
localhost:4777/project/syrher
```

## GET /item/:category/:id_in_category

Calls to 

```
localhost:4567/item/typus/9
```

will go to

```
localhost:4777/èntity/1242001 
```

## GET /books/:alias

Calls to 

```
localhost:4567/books/Boisseree1844
```

will go to

```
localhost:4777/entity/1376714
```
