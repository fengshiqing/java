
### 已commit 未 push，如何回退？
执行：git reset --hard HEAD^，可以回退 上一次的 commit，撤销commit后，之前的修改不会保留在本地，都被还原了。
执行：git reset --soft HEAD^，可以回退 上一次的 commit，撤销commit后，之前的修改会保留在本地，可以直接重新发起commit。

