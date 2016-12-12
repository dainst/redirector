# Rest Api Reference

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