<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.my-tab{
    position:fixed;
    bottom:1px;
    Z-index:999;
    height:60px;
    width:100%;
    background-color: #f7f7fa;
}
.my-tabbar{
    display:-webkit-flex;
    display: flex;
    flex-direction:row;
    justify-content:space-around ;
    align-items:center ;
    align-content:center ;
}
.my-tab-item{
    display:-webkit-flex;
    display: flex;
    flex-direction:column;
    justify-content:center ;
    align-items:center ;
    align-content:center;
    margin-top:10px;
}
.my_tabbar_icon {
  margin: 0 auto;
  width: 24px;
  height: 24px;
}
.blank_div{
    width:100%;
    height:100px;
}
.badge{
    display: inline-block;
    min-width: 1px;
    height: 5rpx;
    line-height: 5px;
    font-size: 10px;
    text-align: center;
    font-family: -apple-system-font, Helvetica Neue, Helvetica, sans-serif;
    text-rendering: optimizeLegibility;
    -webkit-font-smoothing: antialiased;
    color: #FFF;
    background: #f5342f;
    border-radius: 50%;
    overflow: hidden;
    line-height: 15px;
    font-size: 10px;
    text-indent: 5px;
    padding: 0 5px 5 1px;
}
</style>
<div class="blank_div"></div>
<div class="my-tab">
    <div class="my-tabbar">
        <a href="${wechatPath}index/index.action" class="my-tab-item">
               <img src="${wechatPath}icon/work.png" alt="" class="my_tabbar_icon">
                <p class="weui-tabbar__label">工作台</p>
        </a>
        <a href="${wechatPath}index/message.action" class="my-tab-item">
                <div>
                   <img src="${wechatPath}icon/message.png" alt=""class="my_tabbar_icon">
                   <p class="badge">${messageCount==0?'':messageCount}</p>
               </div>
                <p class="weui-tabbar__label">消息</p>
        </a>
        <a href="${wechatPath}setup/index.action" class="my-tab-item">
               <img src="${wechatPath}icon/setting.png" alt="" class="my_tabbar_icon">
                <p class="weui-tabbar__label">设置</p>
        </a>

    </div>
</div>


