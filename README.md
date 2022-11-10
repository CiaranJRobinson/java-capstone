
# Many Steps, Little Leaves

Many Steps, Little Leaves is a Step Tracker database, where you can create an account and store your daily step counts. The chart shows your progress throughout a years time to see if you’re meeting your long term goals. An added functionality lets you ID plants you find on your walks!
## Trello Overview
https://trello.com/invite/b/pqeFcAbV/ATTI1b8e46dd68a2885425a109b3826fbb9419B1B3D6/many-steps-little-leaves
## Demo

Youtube demo: https://youtu.be/BzBTbQu69hQ


## API Reference

#### Get all steps data from steps chart

```http
  GET /api/v1/steps
```
#### Get all steps by userID

```http
  GET /api/v1/user/${userid}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userid`      | `long` | **Required**. Id of user to fetch |



