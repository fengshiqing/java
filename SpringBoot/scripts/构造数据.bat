# 将 source.txt 文件中的内容复制 50 遍，然后输出到 dest.txt 文件中
for /l %%n in (1,1,50) do (
	type source.txt >> dest.txt
)