DrawBarSA2Plot <-
  function(dist1,
           dist2,
           out_file,
           plot_main_title,
           plot_xlab,
           plot_ylab,
           xlabels,
           legend,
           sa2) {
    CreateDir(dirname(out_file))
    pdf(out_file)
    counts <- matrix(c(dist1, dist2), ncol = 2)
    dplot <- barplot(
      t(counts),
      main = plot_main_title,
      col = c("blue", "red"),
      beside = TRUE,
      border = NA,
      las = 1,
      cex.lab = 0.8,
      cex.axis = 0.8,
      cex.main = .9,
      cex.sub = .8,
      xaxs = "i",
      cex.names = 0.8,
      xaxt = "n",
      names.arg = xlabels
    )
    
    box(bty = "l")
    mtext(
      side = 1,
      at = colMeans(dplot),
      line = 0.1,
      text = rownames(counts),
      cex = 0.4,
      las = 1
    )
    legend(
      "topright",
      fill = c("blue", "red"),
      legend = legend,
      bty = "n",
      cex = 0.8,
      border = NA
    )
    title(xlab = plot_xlab,
          line = 1.5,
          cex.lab = .8)
    title(ylab = plot_ylab,
          line = 2.5,
          cex.lab = .8)
    mtext(paste(sa2, "SA2"), cex = 0.6)
    axis(1, at = dplot[seq(1,length(dplot),2)], labels = xlabels, tcl = -0.25)
    dev.off()
  }

DrawErrorFrequencyPlot <- function(dist1, dist2,plot_main_title){
  counts <- matrix(c(dist1, dist2), ncol = 2)
  dplot <- barplot(
    t(counts),
    main = plot_main_title,
    col = c("gary", "black"),
    beside = TRUE,
    border = NA,
    las = 1,
    cex.lab = 0.8,
    cex.axis = 0.8,
    cex.main = .9,
    cex.sub = .8,
    xaxs = "i",
    cex.names = 0.8,
    xaxt = "n"
  )
}