- 初始化日期时间
- 时区问题
- 日期时间格式化、解析
- 日期时间计算
- MySQL 时间字段类型有关的时区问题
> timestamp 保存时根据当前时区转为 UTC,查询时再根据当前时区从 UTC 转回来，datetime 就是一个死的字符串
> http://cdn.oreillystatic.com/en/assets/1/event/36/Time%20Zones%20and%20MySQL%20Presentation.pdf