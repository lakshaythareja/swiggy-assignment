Schema Design
Album => ID, name, tracks
Artist => ID, name
Song => ID, name, url, artistId, albumId, likes, plays
SongLike => ID, songId, userId
SongPlay => ID, songId, userId, createdAt
SongTags => ID, songId, tagId
Tags => ID, name
User => ID, name


APIs :
1) Create Album => Parameters : name
Ex : curl  -X POST -d "name=New Songs" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/album/create

2) Create Artist => Parameters : name
Ex :  curl  -X POST -d "name=Kumar Sanu" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/artist/create

3) Create Song => Parameters : name, albumId, artistId
Ex :  curl  -X POST -d "name=O Mere Sanam&artistId=7&albumId=13" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/song/create

4) Delete Song => Parameters : name
Ex :  curl  -X POST -d "name=O Mere Sanam" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/song/delete

5) Get Song Details => Parameters : name
Ex :  curl  -X GET http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/song/get?name="Tera%20Suroor"

6) Tag a song => Parameters : name (Song name), tagId
Ex : curl  -X POST -d "name=O Mere Sanam&tagId=26" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/song/tag

7) Create Tags => Parameters : name
Ex : curl  -X POST -d "name=New" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/tags/create

8) Create User => Parameters : name
Ex : curl  -X POST -d "name=John Smith" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/user/create

9) Like a song => Parameters : userId, songId
Ex : curl  -X POST -d "userId=20&songId=45" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/like

10) Play a song => Parameters: userId, songId
Ex : curl  -X POST -d "userId=20&songId=45" http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com/play

11) Explore => Parameters : List of tags
Ex : curl http://Swiggy-env-2.5ipvppejxn.ap-south-1.elasticbeanstalk.com:5000/explore\?tags\=26,30,31,32