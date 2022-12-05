#encoding=utf-8
import os
import re
import sys
import struct
from itertools import groupby


if __name__ == '__main__':
    all_lst = open(sys.argv[1], 'r')
    for line in all_lst:
        line=line.strip()
        title = line.split('\t')[0]
        url = line.split('\t')[1]
        print("<tr>")
        print("    <td width=\"150\" align=\"center\"></a></td>")
        print("    <td width=\"800\">%s</td>"%(title))
        print("    <td width=\"200\"><a href=\"https://arxiv.org/pdf/"+url+".pdf\">pdf</a></td>")
        print("</tr>")
