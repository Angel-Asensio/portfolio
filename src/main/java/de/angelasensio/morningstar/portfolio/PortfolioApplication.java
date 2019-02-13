package de.angelasensio.morningstar.portfolio;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@SpringBootApplication
public class PortfolioApplication {

    private static final Log LOG = LogFactory.getLog(PortfolioApplication.class);

	public static void main(String[] args) throws IOException {
        simplePortfolioGenerator();
    }

    private static void simplePortfolioGenerator() throws IOException {
        List<Stock> portfolio = new ArrayList<>();

        String[] companies = new String[] {
                "ABC",
                "ADI",
                "AMGN",
                "AXP",
                "BK",
                "BKNG",
                "BRK.B",
                "CMCSA",
                "EBAY",
                "EPD",
                "FB",
                "GD",
                "GOOG",
                "LOW",
                "MCK",
                "MMP",
                "NVS",
                "OMC",
                "ORCL",
                "PEP",
                "SCHW",
                "SNY",
                "UL",
                "UTX",
                "V",
                "WFC",
                "WPP",
                "ABC",
                "ANTM",
                "BIDU",
                "BKNG",
                "BLK",
                "COO",
                "EBAY",
                "ENB",
                "FB",
                "GOOG",
                "GOOGL",
                "IQV",
                "KMX",
                "LOW",
                "MA",
                "MSFT",
                "ORLY",
                "TCEHY",
                "UNH",
                "V",
                "VTR",
                "AAPL",
                "ABEV",
                "ACN",
                "ADBE",
                "ADSK",
                "AMAT",
                "AMT",
                "AMZN",
                "APD",
                "ASR",
                "ATVI",
                "AVGO",
                "AXP",
                "BA",
                "BABA",
                "BAC",
                "BDX",
                "BF.B",
                "BIDU",
                "BKNG",
                "BLKB",
                "BTI",
                "CAH",
                "CBRE",
                "CBS",
                "CERN",
                "CL",
                "CLB",
                "CMCSA",
                "CME",
                "CMI",
                "CMP",
                "CNI",
                "COST",
                "CRM",
                "CSCO",
                "CSGP",
                "CTRP",
                "CTSH",
                "CVS",
                "D",
                "DEO",
                "DFS",
                "DG",
                "DIS",
                "EA",
                "EFX",
                "EL",
                "ENB",
                "EW",
                "EXPE",
                "FB",
                "FDS",
                "FDX",
                "FMX",
                "GPN",
                "GSK",
                "HAL",
                "HD",
                "HOG",
                "HON",
                "HSY",
                "ICE",
                "ICLR",
                "ILMN",
                "INFO",
                "INTC",
                "INTU",
                "IPG",
                "ISRG",
                "JD",
                "JLL",
                "JNJ",
                "JPM",
                "KMX",
                "KO",
                "LIN",
                "LKQ",
                "LLY",
                "LUV",
                "MA",
                "MAR",
                "MCD",
                "MDLZ",
                "MDT",
                "MELI",
                "MLM",
                "MMM",
                "MRK",
                "MSCI",
                "MSFT",
                "MTCH",
                "NCLH",
                "NFLX",
                "NKE",
                "NOW",
                "NVDA",
                "OMAB",
                "ORLY",
                "OXY",
                "PAC",
                "PCAR",
                "PFE",
                "PG",
                "PHG",
                "PM",
                "PNR",
                "PXD",
                "PYPL",
                "QCOM",
                "RTN",
                "SAP",
                "SBUX",
                "SCHW",
                "SHW",
                "SLB",
                "SRCL",
                "STE",
                "STZ",
                "SYF",
                "TEL",
                "TIF",
                "TJX",
                "TMO",
                "TROW",
                "TRP",
                "TRU",
                "TSM",
                "TTWO",
                "TV",
                "TXN",
                "UA",
                "UNP",
                "UPS",
                "USB",
                "VAR",
                "VMC",
                "WDAY",
                "WMT",
                "WP",
                "YUMC",
                "ZBH"
        };

        BigDecimal priceEarningsRatioLimit = new BigDecimal(15);

        Map<String, Stock> stocks = new TreeMap<>(YahooFinance.get(companies));

        stocks.forEach((k, v) -> filterByPER(priceEarningsRatioLimit, v, portfolio));

        LOG.info("--------------------------------------------------");
        LOG.info("Portfolio size: " + portfolio.size() + " positions");
        LOG.info("--------------------------------------------------");
        portfolio.forEach(PortfolioApplication::printStockInfo);
    }

    private static void printStockInfo(final Stock s) {
        System.out.println(s.getSymbol() + "|" + s.getStats().getPe());
    }

    private static void filterByPER(final BigDecimal priceEarningsRatioLimit, final Stock v, final List<Stock> portfolio) {
        if (Objects.nonNull(v.getStats().getPe()) && v.getStats().getPe().compareTo(priceEarningsRatioLimit) < 0) {
            portfolio.add(v);
        }
    }

}

