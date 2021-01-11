import {Browser, LatLng, Layer, polygon} from 'leaflet';
import {LayerEventControlOptions} from './layer-event-control-options';
import {CovidDataMap} from './covid-data-map';

export class ProvinceCoordinates {
  public provinceName: string;
  public provinceId: string;
  public coordinates: number[][][];
  public border: number[][][];
  private covidData: CovidDataMap;
  private bezirkData: CovidDataMap[];

  constructor(provinceName: string, provinceId: string, coordinates: number[][][], border: [][]) {
    this.provinceName = provinceName;
    this.provinceId = provinceId;
    this.coordinates = coordinates;
    this.border = border || [];
    this.bezirkData = this.coordinates.map(item => ({
      casesPer100Thousand: Math.random() * 500,
      geoId: provinceId,
      provinceId,
      activeCases: 12,
      provinceName,
      normalBeds: 12,
      intensiveBeds: 13,
      deaths: 14
    }));
  }

  createBezirkeMapLayers(eventOptions: LayerEventControlOptions): Layer[] {
    return this.coordinates.map((item, index: number) => polygon(item.map(c => c.map(i => [i[0], i[1]])), {
      attribution: this.provinceId, ...this.defaultStyle(this.bezirkData[index]),
    })
      .on('click', (event => {
        console.log(event);
        if (eventOptions && eventOptions.onClick) {
          eventOptions.onClick(event);
        }
      }))
      .on('mouseover', (event => {
        this.highlightFeature(event, this.bezirkData[index]);
        if (eventOptions && eventOptions.onMouseEnter) {
          eventOptions.onMouseEnter(event);
        }
      }))
      .on('mouseout', (event => {
        this.resetHighlight(event, this.bezirkData[index]);
        if (eventOptions && eventOptions.onMouseOut) {
          eventOptions.onMouseOut(event);
        }
      })));
  }

  createBorderMapLayers(eventOptions: LayerEventControlOptions): Layer[] {
    console.log(this.border);
    console.log(this.provinceName);
    return this.border.map(region => polygon(region.map(item => new LatLng(Number(item[0]) > Number(item[1]) ? Number(item[0]) : Number(item[1]), Number(item[0]) > Number(item[1]) ? Number(item[1]) : Number(item[0]))),
      {
        attribution: this.provinceId, ...this.defaultStyle(this.covidData),
      })
      .on('click', (event => {
        console.log(event);
        if (eventOptions && eventOptions.onClick) {
          eventOptions.onClick(event);
        }
      }))
      .on('mouseover', (event => {
        this.highlightFeature(event, this.covidData);
        if (eventOptions && eventOptions.onMouseEnter) {
          eventOptions.onMouseEnter(event);
        }
      }))
      .on('mouseout', (event => {
        this.resetHighlight(event, this.covidData);
        if (eventOptions && eventOptions.onMouseOut) {
          eventOptions.onMouseOut(event);
        }
      })));
  }

  highlightFeature(event: any, data: CovidDataMap): any {
    const layer = event.target;

    layer.setStyle({
      weight: 4,
      opacity: 1,
      color: this.coloringForCases(data),
      fillColor: this.coloringForCases(data),
      fillOpacity: 0.2,
    });

    if (!Browser.ie && !Browser.opera && !Browser.edge) {
      layer.bringToFront();
    }
  }

  private coloringForCases(data: CovidDataMap): string {
    if (data.casesPer100Thousand < 100) {
      return '#72C600';
    }
    if (data.casesPer100Thousand < 150) {
      return '#DE6501';
    }
    if (data.casesPer100Thousand < 200) {
      return '#E5B100';
    }
    if (data.casesPer100Thousand < 250) {
      return '#E57D00';
    }
    if (data.casesPer100Thousand < 300) {
      return '#E55E00';
    } else {
      return '#E50000';
    }
  }

  resetHighlight(event: any, data: CovidDataMap): any {
    const layer = event.target;

    layer.setStyle(this.defaultStyle(data));
  }

  defaultStyle(data: CovidDataMap): any {
    return {
      weight: 2,
      opacity: 0.4,
      color: this.coloringForCases(data),
      fillColor: this.coloringForCases(data),
    };
  }

  createColoredLayers(color: string): Layer[] {
    return this.coordinates.map(item => polygon(
      item.map(c => c.map(i => [i[0], i[1]])),
      {fill: true, fillColor: color, opacity: 0.6, weight: 0}));
  }

  updateCovidInfo(covidDataMap: CovidDataMap): void {
    console.log(covidDataMap);
    this.covidData = covidDataMap;
  }
}

