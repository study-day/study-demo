```
 <build>
        <plugins>
            <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>versions-maven-plugin</artifactId>
                    <version>2.7</version>
            </plugin>
        </plugins>
    </build>
```
统一管理项目的版本号。方便版本升级。


只需要执行mvn -N versions:update-child-modules则会自动把子POM的<parent>标签中的version更新为和父POM一致。这样修改一处然后运行一下执行一下命令就可以达到统一修改版本号的目的了。（在父model上执行后，所有子model中parent中的version都会修改）


mvn  versions:update-child-modules: 自动把子POM的<parent>标签中的version更新为和父POM一致

mvn versions:set -DnewVersion=0.0.2-SNAPSHOT:更新的父及子Module的版本号都改成了0.0.2-SNAPSHOT.

mvn versions:commit ：如果没有在父pom用引入插件，Maven还会生成一个pom.xml.versionsBackup的备份文件，还需要mvn versions:commit提交

如果没有在父pom用引入插件，

```
 <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>versions-maven-plugin</artifactId>
            <version>2.3</version>
            <configuration>
                <generateBackupPoms>false</generateBackupPoms>
            </configuration>
        </plugin>
```
Maven还会生成一个pom.xml.versionsBackup的备份文件，还需要mvn versions:commit提交



 或者执行

mvn versions:set -DnewVersion=2.0.0-SNAPSHOT -f pom.xml
然后项目中的依赖以及聚集的依赖版本都会被一起更新，也可以采用通配符来进行匹配

mvn versions:set -DgroupId=org.apache.maven.* -DartifactId=* -DoldVersion=2.* -DnewVersion=2.1.0-SNAPSHOT