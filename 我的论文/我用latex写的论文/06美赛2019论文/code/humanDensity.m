figure(1)
z=xlsread('..\peopleDensity.xls');
x=z(:,1);
y=z(:,2);
z=z(:,3);
xx=linspace(min(x),max(x),100); 
yy=linspace(min(y),max(y),100); 
[xt,yt]=meshgrid(xx,yy); 
zt=griddata(x,y,z,xt,yt,'v4'); 

subplot(1,2,2)
h=pcolor(xt,yt,zt);%Concentration map 
view(90,90)
shading interp;
colorbar;
xlabel('Grids along latitude')
ylabel('Grids along longitude')

subplot(1,2,1)
contour(xt,yt,zt); %Plot contour curves 
view(90,90)
colorbar
xlabel('Grids along latitude')
ylabel('Grids along longitude')