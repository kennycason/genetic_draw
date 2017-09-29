遗传算法与重现画像
============

遗传算法是一种解决最优化的搜索算法。以下的遗传算法能用重现画像。

### 算法概念

这编程中的遗传算法有两种。各算法都是用遗传算法把随机的画像进化成目标的画像。
个体的遗传是基因序列，各基因表示一个形状的造成。可能性的形状包含圆，椭圆，三角形，四角形，矩形，多边形，像素。除了形状以外基因还包容别的信息。各基因包含形状的颜色，透明度，位置（x,y,z轴)，大小。

基因表达过程很直接，个体基因中表示的形状画在黑色的画像上（根据z轴顺序）。因为这应用的进化从完全随机个体的种群开始，所以第一代表达的基因的结果平均跟目标画像完全不像。基因表达后，通过目标画像和表达画像比较我们来算个体的适应度。这个比较函数是一种适应函数。在这个应用我们用的适应函数是算目标画像和表达画像的各像素的误差，把各误差都加起来算个体的总体误差。这个适应度会来决定谁生存谁死，也叫适者生存。

在很多应用高的适应度更好，但是在这个应用因为我们在算错误（错误函数），所以完美的适应度是零。零的适应度表达进化的个体表达的画像跟目标画像一摸一样。用更正式的写法：错误函数等于最大适应度减错误。以后我说适应度高的意思就是误差靠近零.

#### 算法参数

1. 种群规模：模型个体的数目。
2. 基因串长度：个体中染色体的数目。
3. 交叉概率：两个个体（A和B）繁殖的时候，孩子从A个体收到基因的概率。收到B个体基因的概率是1.0减收到A个体基因的概率。
4. 突变概率：交叉或基因复制的时候，发生突然变化的概率。可选择定数的概率，也可选择有变化的概率。通过一步一步改成突变概率这个算法有点像模拟退火算法。
5. 适应函数：可算个体的适应度的函数。画像比较有几种算法。比如，RGB直（赤、绿、青）的一个数量分开成R和G和B的三个质量来比较。R也会再分开边RH（赤高位）和RL（赤低位）。
6. 利用的形状：这应用可以选择基因能表达什么形状。
7. 子代选择函数：选择谁生存繁殖，谁死。通过精英主义选择最强的个体的遗传会到下一代。在单亲遗传算法，最精英个体的遗传直接复制到下一代，在双亲遗传算法，保证最精英的个体有机会跟别的种群中的个体繁殖造成子代。双亲遗传算法有另外一个参数。适应度高的个体需要选择适合配偶繁殖。有几种方法选择。这应用可以选在两种，联赛选择算法和随机接受选择算法。联赛选择算法会随机选择几个个体（竞争者）。从这个小种群中适应性最高个体们繁殖造成子代。通过随机接受选择算法，适应性高的个体选择像他们一样适应高的个体繁殖的概率比选在适应性低的个体高。简单说强跟强在一起，弱跟弱在一起，当然如果弱的有机会跟强繁殖他们不会拒绝，但是强的个体像跟弱的个体繁殖的概率比较低。这个选择算法模拟大自然的优胜劣汰。


#### 单亲遗传算法 ([SingleParentGeneticDraw.kt](https://github.com/kennycason/genetic_draw/blob/master/src/main/java/com/kennycason/genetic/draw/SingleParentGeneticDraw.kt))

1. 随机造成一个亲体。
2. 复制亲体的遗传造成孩子。各基因根据突变概率通过突变。
3. 评价孩子的适应度。
4. 如果孩子的适应度比亲体的高，亲体就死，孩子就变成亲体。
5. 回到第二部，继续到满足亲体的适应度.

#### 双亲遗传算法 ([PopulationBasedGeneticDraw.kt](https://github.com/kennycason/genetic_draw/blob/master/src/main/java/com/kennycason/genetic/draw/PopulationBasedGeneticDraw.kt))

1. 从完全随机个体的种群开始。
2. 评价个体的适应度。根据适应度排序个体。
3. 可选择最精英个体生存到下一代。
4. 可把适应度最低的一部分杀掉。他们不能繁殖。很现实。:’(
5. 根据子代选择函数，决定谁有权利繁殖造成下一代。
6. 回到第二部，继续到满足亲体的适应度。

### 特别的结果和统计

两个进化我最喜欢的口袋妖怪（妙蛙种子）。用双亲遗传算法，随机接受算法，精英选择。

<img src="output/bulbasaur_evolved_polygon.png?raw=true" height="250px"/> <img src="output/bulbasaur_evolved_polygon2.png?raw=true" height="250px"/>

两个GIF显示黄色四角的进化。

<img src="output/square_evolution.gif?raw=true"/> <img src="output/square_evolution2.gif?raw=true"/>

两个GIF显示DataRank公司和Simply Measured公司标志的进化。

<img src="output/datarank_whale_evolved.gif?raw=true" height="150px"/> <img src="output/sm_logo_evolved.gif?raw=true" height="150px"/>

马里奥的进化。 左边的用多边形。中间和右边用像素。

<img src="output/mario_evolved_polygon.png?raw=true" width="128px"/> <img src="output/mario_evolved_pixel4.png?raw=true" width="128px"/> <img src="output/mario_evolved_pixel8.png?raw=true" width="128px"/>

耀西的进化和收敛速度。

<img src="output/evolving_yoshi_with_stats.png?raw=true" width="600px"/>

以下是我老婆头像进化的结果。虽然利用透明的颜色会让算法性能变低，进化结果比较好。左边和中间的画像是利用透明的颜色。右边没有透明的颜色。

<img src="output/jing_evolved_2500_genes.png?raw=true" width="250px"/> <img src="output/jing_evolved.png?raw=true" width="250px"/> <img src="output/jing_evolved_no_alpha.png?raw=true" width="250px"/>

我开发这个遗传算法的时候我发现几个网站有重现蒙娜丽莎的算法。我看了这个决定试试看。结果不错。以下有三个不同的进化，每一个用不同的参数。

<img src="output/mona_lisa_evolved_1000_genes.png?raw=true" width="250px"/> <img src="output/mona_lisa_evolved_2000_genes.png?raw=true" width="250px"/> <img src="output/mona_lisa_evolved_polygon.png?raw=true" width="250px"/>

以下的三个图片表示突变概率的对学习速度影响。（为了更容易理解结果以后我打算把这三个结果表示在同一个图片）果然突变概率10%比1%和50%有效。

<img src="output/single_parent_mutation_1_percent.png?raw=true" width="400px"/>

<img src="output/single_parent_mutation_10_percent.png?raw=true" width="400px"/>

<img src="output/single_parent_mutation_50_percent.png?raw=true" width="400px"/>

虽然以上的图片不表示，最有效的是开始用高的突变概率，个体越进化越低的突变概率更合适。个体适应度越高，太大的突变大多分的时候不会出生更适应性高的孩子，所以低的突变概率在这时候更好点。开始，因为个体适应度非常低，所以大的突变有更大的概率可以出生比亲体适应性高的孩子。

<img src="output/static_vs_dynamic_mutation_probability.png?raw=true" width="300px"/>

我们从任天堂的好友，Kirby的进化。

<img src="output/kirby_evolved_pixel4.png?raw=true" height="128px"/> <img src="output/kirby_evolved_polygon.png?raw=true" height="128px"/> <img src="output/kirby_evolved_bad_fitness_function.png?raw=true" width="120px"/>

但是, 看一下右边的进化的Kirby。他明显有点奇怪。形状不错，但是颜色不对。这是用不好适应函数的结果。我原来开发的适应函数评价各画像的像素的时候发生了一个小问题。各像素是24bit，高位8bit是赤，中位8bit是绿，低位8bit是青。想起我们以上谈的误差函数，一个像素的误差等于A画像的像素减B画像的像素。根据RGB（赤，绿，青）的数位，因为赤的数位最高，所以赤的误差比别的数位更影响个体的适应。互相，因为青的数位最低，所以青的误差对个体的适应度比较少。

更多统计和图片可以下载看看（XLSX） [这里](convergence_stats.xlsx?raw=true).

最后是我自己头像进化的结果。 :)

<img src="output/my_profile_evolved4.png" width="400px"/>

还有之前的几代。

<img src="output/my_profile_evolved3.png" width="300px"/> <img src="output/my_profile_evolved2.png" width="300px"/>
<img src="output/my_profile_evolved1.png" width="300px"/> <img src="output/my_profile_evolved0.png" width="300px"/>
