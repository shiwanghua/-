function transferFiletoThreeColumns(data)
d=xlsread(data);
[r,c]=size(d);
syms dn;
for i = 1:r
    dn((i-1)*c+1:i*c,1)=i;
    for j =1:c
        dn((i-1)*c+j,3)=d(i,j);
        dn((i-1)*c+j,2)=j;
    end
end
disp(dn);
dn=double(dn);
xlswrite('t2.xls',dn);
end