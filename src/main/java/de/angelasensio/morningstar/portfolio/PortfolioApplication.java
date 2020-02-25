package de.angelasensio.morningstar.portfolio;

import static java.util.Objects.nonNull;

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
                "JPM",
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
                "XOM",
                "ABC",
                "ALGN",
                "AMZN",
                "ANTM",
                "BIDU",
                "BKNG",
                "DIS",
                "ENB",
                "FB",
                "GOOG",
                "GOOGL",
                "IQV",
                "KMX",
                "LOW",
                "LYFT",
                "MA",
                "MSFT",
                "TCEHY",
                "UBER",
                "UNH",
                "V",
                "AAPL",
                "ABEV",
                "ACN",
                "ADBE",
                "ADSK",
                "AMAT",
                "AMD",
                "AMT",
                "ANET",
                "ATVI",
                "AVGO",
                "BA",
                "BABA",
                "BAC",
                "BDX",
                "BIIB",
                "BLK",
                "BMY",
                "CB",
                "CBRE",
                "CDNS",
                "CERN",
                "CL",
                "CLB",
                "CME",
                "CMI",
                "CMP",
                "CNI",
                "COF",
                "COG",
                "COO",
                "COST",
                "CRM",
                "CSCO",
                "CSGP",
                "CTSH",
                "CVS",
                "CVX",
                "CXO",
                "D",
                "DAL",
                "DEO",
                "DFS",
                "DLTR",
                "DPZ",
                "EA",
                "ECL",
                "EFX",
                "EL",
                "EW",
                "EXPE",
                "FDS",
                "FDX",
                "FMX",
                "HAL",
                "HCA",
                "HD",
                "HON",
                "INFO",
                "INTC",
                "INTU",
                "ISRG",
                "JCI",
                "JD",
                "JLL",
                "JNJ",
                "JWN",
                "KLAC",
                "LH",
                "LLY",
                "LUV",
                "MAR",
                "MCD",
                "MCO",
                "MDLZ",
                "MDT",
                "MELI",
                "MMM",
                "MTCH",
                "NFLX",
                "NKE",
                "NOW",
                "NVDA",
                "ORLY",
                "OXY",
                "PCAR",
                "PFE",
                "PM",
                "PXD",
                "PYPL",
                "QCOM",
                "ROST",
                "SAP",
                "SBUX",
                "SHW",
                "SLB",
                "SNPS",
                "SPGI",
                "SPOT",
                "STZ",
                "SYK",
                "TEL",
                "TGT",
                "TJX",
                "TMO",
                "TRP",
                "TRU",
                "TSM",
                "TTWO",
                "TXN",
                "ULTA",
                "UNP",
                "UPS",
                "USB",
                "WDAY",
                "WH",
                "WMT",
                "YUM",
                "YUMC",
                "ZBH"
        };


        Map<String, Stock> stocks = new TreeMap<>(YahooFinance.get(companies));

        stocks.forEach((k, v) -> filterByPER(v, portfolio));

        LOG.info("--------------------------------------------------");
        LOG.info("Portfolio size: " + portfolio.size() + " positions");
        LOG.info("--------------------------------------------------");
        portfolio.forEach(PortfolioApplication::printStockInfo);
    }

    private static void printStockInfo(final Stock s) {
        System.out.println(s.getSymbol() + "|" + s.getStats().getPe());
    }

    private static void filterByPER(final Stock v, final List<Stock> portfolio) {
        if (nonNull(v.getStats().getPe())) {
            portfolio.add(v);
        }
    }

}

