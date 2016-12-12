# Rest Api Reference

If the targetUrl is `localhost:4777` and the mappings file 
is as follows

```
1,project/a
```

then redirects like 

```
localhost:4567/drupal/?q=node/1 -> localhost:4777/project/a 
localhost:4567/drupal/?q=de_DE/node/1 -> localhost:4777/project/a
localhost:4567/drupal/?q=/de_DE/node/1 -> localhost:4777/project/a 
localhost:4567/drupal/?q=/en_EN/node/1 -> localhost:4777/project/a 
```

would get executed.