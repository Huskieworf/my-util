#### about MySql

##### 1.add

``` java
 Connection conn = MySqlUtil.getConnection();
        PreparedStatement pst = null;
        String sql = " insert into user(username,password,nickname,email,registerTime) values (?,md5(?),?,?,?) ";
        boolean flag = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,user.getUsername());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getNickname());
            pst.setString(4,user.getEmail());
            pst.setString(5,user.getRegisterTime());
            System.out.println(user.toString());
            flag = pst.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MySqlUtil.close(null,pst,conn);
        }
        return  flag;
```

##### 2.sign in

``` java

```

##### 3.select

``` java

```

##### 4.verification 创建一个简单的验证码

``` java
private void verification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //告诉浏览器响应的是一张图片
        response.setContentType("image/jpeg");
        //创建一个图片对象
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        //获取图片的画笔
        Graphics2D graphics2D  = image.createGraphics();
        //设置画笔颜色
        int r = (int) (Math.random()*127)+127;
        int g = (int) (Math.random()*127)+127;
        int b = (int) (Math.random()*127)+127;
        graphics2D.setColor(new Color(r, g, b));
        //填充一个矩形
        graphics2D.fillRect(0, 0, 80, 20);
        //重新设置画笔的颜色
        int r1 = (int) (Math.random()*127);
        int g1 = (int) (Math.random()*127);
        int b1 = (int) (Math.random()*127);
        graphics2D.setColor(new Color(r1, g1, b1));
        //设置字体
        graphics2D.setFont(new Font("微软雅黑", Font.ITALIC, 18));
        //确定绘制的内容
        String content = MyStringUtil.randomString(4);
        //将文字保存在seesion中
        HttpSession session = request.getSession();
        session.setAttribute("VAL",content);
        //写文字
        graphics2D.drawString(content,1,18);
        //雪花
        graphics2D.setColor(Color.white);
		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random()*80);
			int y = (int) (Math.random()*38);
            graphics2D.drawString("*", x, y);
		}
		//障碍物
		for (int i = 0; i < 5; i++) {
			//重新设置画笔的颜色
			int r2 = (int) (Math.random()*127);
			int g2 = (int) (Math.random()*127);
			int b2 = (int) (Math.random()*127);
            graphics2D.setColor(new Color(r2, g2, b2));
			int x = (int) (Math.random()*80);
			int y = (int) (Math.random()*38);
			int x1 = (int) (Math.random()*80);
			int y1 = (int) (Math.random()*38);
            graphics2D.drawLine(x1, y1, x, y);
		}

        //销毁画笔
        graphics2D.dispose();
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
```

