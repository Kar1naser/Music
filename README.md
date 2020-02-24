

```
1、进入Login欢迎界面，通过sendEmptyMessageDelayed延迟1s，然后跳转至登录界面mainactivity
2、注册是使用SharedPreferences临时储存实现
3、使用mediaPlayer播放器播放音乐，通过设定全局变量标记当前歌曲的id、标记当前播放状态，循环播放还是随机播放，标记当前歌曲是否正在播放
4、开始/暂停是根据当前歌曲的状态来改变歌曲播放状态
5、设置mediaPlayer监听OnCompletionListener，当前歌曲播放完毕后，再根据当前播放状态（循环播放，随机播放），自动播放下一首歌曲

```