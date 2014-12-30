<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IDMAPING</title>
</head>
<body>

	<!-- 没有写action  -->
	<form id="idmaps" method="post">
		<input type="radio" name="queryType" value="mqq" checked="checked"/>qq 
		<input type="radio" name="queryType" value="imei"/>imei
		<input type="radio" name="queryType" value="imsi"/>imsi
		<input type="radio" name="queryType" value="interWebo"/>interWebo
		<input type="radio" name="queryType" value="sina"/>sina
		
		查询ID：<input type="text" name="queryID" id="queryID"> <input
			type="submit">
	</form>

	<div>
		<div>
			<ul>
				<c:forEach items="${idsets}" var="um">
					<li>${um}</li>
				</c:forEach>
			</ul>
		</div>
		<div id="attribute">
			<table>
				<tr>
					<td>信息列</td>
					<td>内容</td>
				</tr>
				<tr>
					<td>昵称</td>
					<td>${qqRecord.nick}</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>${qqRecord.age}</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>${qqRecord.gender}</td>
				</tr>
				<tr>
					<td>所在地</td>
					<td>${qqRecord.province}${qqRecord.city}</td>
				</tr>

			</table>
		</div>

		<div id="main" style="height: 600px; width: 400"></div>
	</div>


	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
		// 路径配置
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		require([ 'echarts', 'echarts/chart/force' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('main'));

			option = {
			
				tooltip : {
					trigger : 'item',
					formatter : '{a} : {b}'
				},
				toolbox : {
					show : true,
					feature : {
						restore : {
							show : true
						},
						magicType : {
							show : true,
							type : [ 'force', 'chord' ]
						},
						saveAsImage : {
							show : true
						}
					}
				},
				series : [ {
					type : 'force',
					name : "人物关系",
					ribbonType : false,
					categories : [ {
						
						name : 'QQ好友',
						name : 'WEIBO好友'

					} ],
					itemStyle : {
						normal : {
							label : {
								show : true,
								textStyle : {
									color : '#555'
								}
							},
							nodeStyle : {
								brushType : 'both',
								borderColor : 'rgba(255,215,0,0.4)',
								borderWidth : 1
							},
							linkStyle : {
								type : 'curve'
							}
						},
						emphasis : {
							label : {
								show : false
							// textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
							},
							nodeStyle : {
							//r: 30
							},
							linkStyle : {}
						}
					},
					useWorker : false,
					minRadius : 15,
					maxRadius : 25,
					gravity : 1.1,
					scaling : 1.1,
					roam : 'move',
					nodes : [ 
							<c:forEach items="${grphx.vectexs}" var="node">
								{
									symbol:"image://../img/qq.jpg",
									symbolSize:8,
									draggable:true,
									category : 0,
									name : '${node.vectexID}',
									label: '${node.vectexDetail}',
									value : 4,
									itemStyle: {
										normal: {
											label: {
												position:"right",
												
											}
										}
									}
								},
							</c:forEach>    
					          
										
					],
					
					
					links : [
					 		<c:forEach items="${grphx.edges}" var="edge">
					         {
									source : '${edge.source}',
									target : '${edge.dest}',
									weight : 3,
									name: "QQ好友",
							},
						</c:forEach>   
					 ]
				} ]
			};
			var ecConfig = require('echarts/config');
			function focus(param) {
				var data = param.data;
				var links = option.series[0].links;
				var nodes = option.series[0].nodes;
				if (data.source !== undefined && data.target !== undefined) { //点击的是边
					var sourceNode = nodes[data.source];
					var targetNode = nodes[data.target];
					console.log("选中了边 " + sourceNode.name + ' -> '
							+ targetNode.name + ' (' + data.weight + ')');
				} else { // 点击的是点
					console.log("选中了" + data.name + '(' + data.value + ')');
				}
			}
			myChart.on(ecConfig.EVENT.CLICK, focus)

			myChart.on(ecConfig.EVENT.FORCE_LAYOUT_END, function() {
				console.log(myChart.chart.force.getPosition());
			});

			// 为echarts对象加载数据 
			myChart.setOption(option);
		});
	</script>
</body>
</html>