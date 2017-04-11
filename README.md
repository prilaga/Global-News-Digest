# Global-News-Digest

This is a demo app named Global News Digest.
I used MVP pattern, Dependency Injection with Dagger 2, request to https://newsapi.org API with Retrofit 2.2 and all working with RxJava.
- For test purposes I added ability to change base url of API and added Mock server (it's working offline), so you able to change it in the settings of the app.
- Implemented cache feature. All data stored in the cache and retrieved from it by request parameters. So I avoid additional data loading from network.
- Created custom card views for Sources and Articles of news.
- Added ability to change sources of news.
- Created animated views and placed on toolbar

### APK file: [download](https://www.dropbox.com/s/9e1z09p6ijgtvfj/Global%20News%20Digest%201.0.2-debug.apk?dl=0)

### Infomation

- Author：[Oleg Tarashkevich](http://prilaga.com)
- Website: [Prilaga.com](http://prilaga.com)
- Google Play: [Developers page](https://play.google.com/store/apps/dev?id=5246074920016878549)

<img src="https://github.com/OlegTarashkevich/Global-News-Digest/blob/master/device-2017-04-09-232722.png?raw=true" width="444">
