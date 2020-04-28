figure
z=xlsread('..\trafficDensity.xls');
x=z(:,1);
y=z(:,2);
z=z(:,3);
xx=linspace(min(x),max(x),100); 
yy=linspace(min(y),max(y),100); 
[xt,yt]=meshgrid(xx,yy); 
zt=griddata(x,y,z,xt,yt,'v4'); 
h=pcolor(xt,yt,zt);%Concentration map 
view(90,90)
shading interp;
colorbar;
xlabel('Grids along latitude')
ylabel('Grids along longitude')