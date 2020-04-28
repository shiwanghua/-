#import numpy as np
#import pandas as pd
#import seaborn as sns
import folium
import webbrowser

from folium import Marker

# define the world map
world_map = folium.Map(location=[100.0, 0.0],tiles='OpenStreetMap', zoom_start=6,control_scale=True)
# display world map

temp0 = folium.GeoJson(data={"type": "MultiLineString",
                          "coordinates": [
[[-67.275, 17.947], [-65.606, 17.947]],
                              [[-65.606, 18.515], [-67.275, 18.515]],
                              
                              [[  -67.275,17.947], [-65.606,17.947]],#Left lower and right lower (opposite longitude and latitude from next paragraph) 
                              [[  -67.275,17.947], [-67.275,18.515]],#Left lower left upper 
                              [[ -65.606,18.515], [ -67.275,18.515]],#The upper right is connected with the upper left 
                              [[ -65.606,18.515], [ -65.606,17.947]]#Right lower right upper 

                          ]
                          }).add_to(world_map)
temp1=folium.Marker([18.33,-65.65],popup='Caribbean Medical Center, Jajardo').add_to(world_map)
temp2=folium.Marker([18.22,-66.03], popup='Hospital HIMA, San Pablo ').add_to(world_map)
temp3=folium.Marker([18.44,-66.07], popup='Hosipital Pavia Santurce, San Juan').add_to(world_map)
temp4=folium.Marker([18.40,-66.16], popup='Puerto Rico Childrens Hospital,Bayamon').add_to(world_map)
temp5=folium.Marker([18.47,-66.73], popup='<i>Hospital Pavia Arecibo, Arecibo</i>').add_to(world_map)

#temp6=folium. Marker(location=[18.33,-65.65],popup=folium.popup('Mom & Pop Arrow Shop >>', parse_html=True)).add_to(world_map)#words mark


file_path = r"D:\untitled2\WorldMap\test.html"
world_map.save(file_path)     # save as html file
webbrowser.open(file_path)    # open in default browser